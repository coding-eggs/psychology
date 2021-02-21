function initMenu(routeMap,activePage){
    let obj = new Map();
    for(let i in routeMap){
        obj.set(i,routeMap[i])
    }

    var menuObj = $("#sidebarMenu");
    obj.forEach((value,key)=>{

        if(value.length ===1){
            var active = "";
            value.forEach(e=>{
                if(e.permissionUrl === activePage){
                    active = 'active'
                }
            })
            menuObj.append(
                '<li class='+active+'>' +
                '<a href='+value[0].permissionUrl+ '><i data-feather='+value[0].dataFeather+'></i><span>'+key+'</span>'+'</a>'+
                '</li>'
            )
        }else{
            var active = "submenu";
            var liActive = "";
            var liHtml='';
            value.forEach(e=>{
                if(e.permissionUrl === activePage){
                    liActive = 'active'
                    active = 'active'
                }
                liHtml = liHtml + '<li class='+liActive+'><a href='+e.permissionUrl+'>'+e.pageName+'</a></li>'
                liActive=''
            })
            menuObj.append(
                '<li class='+active+'>' +
                '<a href=#><i data-feather='+value[0].dataFeather+'></i><span >'+key+'</span>'+'<span class="menu-arrow"></span>'+'</a>'+
                '<ul>'+ liHtml+
                '</ul>'+
                '</li>'
            )
        }
    })
}

function getParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);   //对参数进行decodeURI解码
    return null;
}
