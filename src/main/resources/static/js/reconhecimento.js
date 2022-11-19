const cam = document.getElementById('cam')

/*navigator.mediaDevices.enumerateDevices()
    .then(devices =>{
        if(Array.isArray(devices)){
            //tem dispositivos
            devices.forEach(device => {
                if(devices.kind === 'videoinput'){
                    if(device.label.includes('Source 3')){
                        //tem a camera certa
                        navigator.getUserMedia(
                            {video: {
                                deviceId : device.deviceId
                            }},
                            stream => cam.srcObject = stream,
                            error => console.error(error)
                        )
                    }
                }
            })
        }
    })
*/

navigator.getUserMedia({video: true},
    stream => cam.srcObject = stream,
    error => console.error(error) )
