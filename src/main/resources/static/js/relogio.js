function mostrarHora(){
    var data = new Date();
    var hrs = data.getHours();
    var min = data.getMinutes();
    var seg = data.getSeconds();
    
    document.getElementById('horas').innerHTML = hrs;
    document.getElementById('minutos').innerHTML = min;
    document.getElementById('segundos').innerHTML = seg;

}

setInterval(mostrarHora, 10)