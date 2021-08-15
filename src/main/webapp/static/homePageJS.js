
    function field_focus(field, ID) {
    if(field.value == ID) {
    field.value = '';
    }
}

    function field_blur(field, ID) {
    if(field.value == '') {
    field.value = ID;
    }
}

    function field_focus(field, Password) {
    if(field.value == Password) {
    field.value = '';
    }
}

    function field_blur(field, Password) {
    if(field.value == '') {
    field.value = Password;
    }
}

    //Fade in dashboard box
    $(document).ready(function(){
    $('.box').hide().fadeIn(1000);
});

    //Stop click event
    $('a').click(function(event){
    event.preventDefault();
});
