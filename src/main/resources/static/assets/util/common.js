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
            var liHtml='';
            value.forEach(e=>{
                liHtml = liHtml + '<li><a href='+e.permissionUrl+'>'+e.pageName+'</a></li>'
                if(e.permissionUrl === activePage){
                    active = 'active'
                }
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
