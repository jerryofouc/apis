$(document).ready(function(){
    $('#li-clientapp').addClass("active");
    $("#client-form").validate({
        rules:{
            name:"required",
            clientId:{
                required:true,
                remote:{
                    url: "isUnique",
                    type: "get",
                    data: {
                        username: function() {
                            return $('#clientId-input').val();
                        }
                    }
                }
            },
            redirectUris:"required",
            contactName:"required",
            contactEmail:"required email",
            thumbNailUrl :"required url",
            expireDuration: "required digits"
        },
        messages:{
            name:"名字不能为空",
            clientId:{
                required:"不为空",
                remote:"不重复"
            },
            redirectUris : {
                required:'不为空',
                url: '且必须使用正确的网址'
            },
            contactName:"不为空",
            contactEmail:{
                required:'不为空',
                email: '且必须使用正确的email'
            },
            thumbNailUrl: "不为空，且必须使用正确的网址",
            expireDuration:{
                required:'不为空',
                digits: '必须使用整数'
            }
        }

    });
 });

//动态增加RedirectInput
function addRedirectInput(){
    var redirectInput = '<div class="controls" style="margin-top:10px"> \
                         <input class="input-xlarge focused"  name="redirectUris" value="" placeholder="http://johndoe.com" required pattern="(http|https)://.+"  type="text" > \
                          <button class="btn btn-primary btn-add btn-mini" onclick="addRedirectInput()">\
                            <i class="icon-plus-sign icon-white"></i>添加\
                          </button>\
                          </div>';
    var deleteButton = '<button class="btn btn-primary btn-mini btn-delete" onclick="$(this).parent().remove()"><i class="icon-trash icon-white"></i>删除</button>';
    $('.btn-add').replaceWith(deleteButton);
    $('#redirectInputs').append(redirectInput);
}
function checkClientId(){
    var clientId = $('#clientId-input').val();
    if(clientId){
        $.getJSON( "isUnique?"+"clientId="+ clientId , function( data ) {
            if(data){
                $('#clientId-tip').text('OK').css('color','green').show().delay(1000).fadeOut();
            }else{
                $('#clientId-tip').text('重复id').css('color','red').show().delay(1000).fadeOut();
            }
        });
    }else{
        $('#clientId-tip').text('不能为空').css('color','red').show().delay(1000).fadeOut();
    }
}


