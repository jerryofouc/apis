$(document).ready(function(){
    $('#li-resourceserver').addClass("active");
 });


//动态增加RedirectInput
function addScope(){
    var redirectInput = '<div class="controls" style="margin-top:10px"> \
                         <input class="input-xlarge focused"  name="scopes" value="" placeholder="scope" required type="text" > \
                          <button class="btn btn-primary btn-add btn-mini" onclick="addScope()">\
                            <i class="icon-plus-sign icon-white"></i>添加\
                          </button>\
                          </div>';
    var deleteButton = '<button class="btn btn-primary btn-mini btn-delete" onclick="$(this).parent().remove()"><i class="icon-trash icon-white"></i>删除</button>';
    $('.btn-add').replaceWith(deleteButton);
    $('#scopes').append(redirectInput);
}