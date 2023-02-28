    function showAlert(msg){
        $('#alert').css('display','flex')
        .find('div>div')
        .first().text(msg)
        .next().find('a').click(function(e){
            $(e.target).closest('#alert').hide();
            return false;
        });
    }