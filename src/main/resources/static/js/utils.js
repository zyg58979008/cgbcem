/**
 * Created by xinxi on 2018-11-18.
 */
function num(obj){
    if(isNaN(obj.value) && !/^-$/.test(obj.value)){
        obj.value="";
    }
    if(!/^[+-]?\d*\.{0,1}\d{0,1}$/.test(obj.value)){
        obj.value=obj.value.replace(/\.\d{2,}$/,obj.value.substr(obj.value.indexOf('.'),3));
    }
}
function formatData(num) {
    num = num + '';
    if (!num.includes('.')) {
        num += '.'
    }
    return num.replace(/(\d)(?=(\d{3})+\.)/g, function($0, $1) {
        return $1 + ',';
    }).replace(/\.$/, '');
}