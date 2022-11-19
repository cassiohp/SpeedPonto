const cam = document.getElementById('cam')

const startVideo = () => {
    navigator.getUserMedia({video: true},
        stream => cam.srcObject = stream,
        error => console.error(error) )    
}

const loadLabels = () => {
    const labels = ['Cassio Henrique']
    return Promise.all(labels.map(async label => {
        const descriptions = []
        for(let i = 1; i <= 3; i++){
            const img = await faceapi.fetchImage(`/lib/face-api/labels/${label}/${i}.jpg`)
            const detections = await faceapi
                .detectSingleFace(img)
                .withFaceLandmarks()
                .withFaceDescriptor()
            descriptions.push(detections.descriptor)
        }
        return new faceapi.LabeledFaceDescriptors(label, descriptions)
    }))
}

Promise.all([
    faceapi.nets.tinyFaceDetector.loadFromUri('/lib/face-api/models'),
    faceapi.nets.faceLandmark68Net.loadFromUri('/lib/face-api/models'),
    faceapi.nets.faceRecognitionNet.loadFromUri('/lib/face-api/models'),
    faceapi.nets.faceExpressionNet.loadFromUri('/lib/face-api/models'),
    faceapi.nets.ageGenderNet.loadFromUri('/lib/face-api/models'),
    faceapi.nets.ssdMobilenetv1.loadFromUri('/lib/face-api/models'),
]).then(startVideo)

cam.addEventListener('play', async () => {
    const canvas = faceapi.createCanvasFromMedia(cam)
    const canvasSize = {
        width: cam.width,
        height: cam.height
    }
    const labels = await loadLabels()
    faceapi.matchDimensions(canvas, canvasSize)
    document.getElementById('face').appendChild(canvas)
    setInterval(async () =>{
        const detections = await faceapi
        .detectAllFaces(
            cam,
            new faceapi.TinyFaceDetectorOptions()
        )
        .withFaceLandmarks()
        .withFaceExpressions()
        .withFaceDescriptors()
        const resizedDetections = faceapi.resizeResults(detections, canvasSize)
        //Taxa de acerto 60%
        const faceMatcher = new faceapi.FaceMatcher(labels, 0.6)
        const results = resizedDetections.map(d =>
            faceMatcher.findBestMatch(d.descriptor)  
        )
        canvas.getContext('2d').clearRect(0,0, canvas.width, canvas.height)
        faceapi.draw.drawDetections(canvas, resizedDetections)
        faceapi.draw.drawFaceLandmarks(canvas, resizedDetections)
        faceapi.draw.drawFaceExpressions(canvas, resizedDetections)
        results.forEach((result, index) =>{
            const box = resizedDetections[index].detection.box
            const {label, distance} = result
            new faceapi.draw.DrawTextField([
                `${label} (${parseInt(distance * 100, 10)})`
            ], box.bottomRight).draw(canvas)
        })
    }, 100)
})