/**
 * 
 */
function changVerifyCode(img) {
    img.src="../Kaptcha?"+Math.floor(Math.random()*100);
}