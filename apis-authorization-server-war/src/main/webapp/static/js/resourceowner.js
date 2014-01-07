$(document).ready(function(){
    $('#li-resourceowner').addClass("active");
    $('#resourceowner-form').validate({
        rules:{
            name:"required",
            email:{
                required:true,
                remote:{
                    url: config.uniqueURL,
                    type: "get",
                    data: {
                        emailUserName: function() {
                            return $('#input-email').val();
                        },
                        id:function(){
                            return  $('#input-id').val();
                        }
                    }
                }
            }
        },
        messages:{
            name:"不为空",
            email:{
                required:"不为空",
                remote:"用户名不重复"
            }
        }
    });
 });