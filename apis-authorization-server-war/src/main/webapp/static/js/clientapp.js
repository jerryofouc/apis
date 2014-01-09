$(document).ready(function(){
    $('#li-clientapp').addClass("active");
    validateForm();
    initScopeSelect(preloadData);
    $('#resourceServerId').change(function(){
        initScopeSelect(null);
    });
 });

function initScopeSelect(preloadData){
    var id = $('#resourceServerId :selected').val();
    $.getJSON( config.scopeURL + id, function( data ) {
        var html = '<select id="scope-select" style="width:300px" name="scopes" class="populate scope-select" multiple>';
        $.each(data,function(key,value){
            html += '<option value="'+key+'">'+value+'</option>'
        })
        html += '</select>';
        $('#scope-div').children().remove();
        $('#scope-div').append(html);
        $('#scope-select').select2();
        $('#scope-select').select2('data',preloadData);
    });
}

//增加RedirectInput
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
    validateForm();//重新绑定一遍事件
}
function validateForm(){
    $("#client-form").validate({
        rules:{
            name:"required",
            clientId:{
                required:true,
                remote:{
                    url: config.uniqueURL,
                    type: "get",
                    data: {
                        clientId: function() {
                            return $('#clientId-input').val();
                        },
                        id:function(){
                            return  $('#input-id').val();
                        }
                    }
                }
            },
            redirectUris:{
                required:true,
            },
            contactName:"required",
            contactEmail:"required email",
            thumbNailUrl :"url",
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
            },
            contactName:"不为空",
            contactEmail:{
                required:'不为空',
                email: '且必须使用正确的email'
            },
            thumbNailUrl: "必须使用正确的网址",
            expireDuration:{
                required:'不为空',
                digits: '必须使用整数'
            }
        }
    });
}



