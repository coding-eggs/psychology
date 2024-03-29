let thispoint;


const context = 'http://127.0.0.1:9527/'
function intThisPoint(point){
    thispoint = point;
}



const service = axios.create({
    // baseURL: "https://ailuoli.cn:9528/psychology",
    baseURL: context,
    timeout: 15000 // 请求超时时间
})


// request拦截器
service.interceptors.request.use(config => {
    config.headers.post['X-Requested-With'] = 'XMLHttpRequest';
    config.headers.get['X-Requested-With'] = 'XMLHttpRequest';
    return config
}, error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error).then(r => console.log(r))
})

// respone拦截器
service.interceptors.response.use(
    response => {
        /**
         * code为非200是抛错 可结合自己业务进行修改
         */
        const res = response.data
        if (res.code !== 200) {
            // 401:未登录;
            if (res.code === 403) {
                thispoint.$confirm('您没有权限访问该请求或页面，可以取消继续留在该页面，或者切换登录', '切换登录', {
                    confirmButtonText: '切换登录',
                    cancelButtonText: '取消',
                    type: 'warning',
                    center: true
                }).then(() => {
                    window.location.href="/login.html"
                }).catch(()=>{
                    thispoint.$message({
                        type: 'info',
                        message: '取消切换登录'
                    })
                })
            }else if(res.code === 10001 || res.code ===10002 || res.code === 10000){
                thispoint.$message({
                    message: res.msg,
                    type: 'error',
                    duration: 3 * 1000
                })
                setTimeout('window.location.href="/login.html"',1000)
            }else{
                thispoint.$message({
                    message: res.msg,
                    type: 'error',
                    duration: 3 * 1000
                })
            }
            return Promise.reject('error')
        } else {
            return response.data
        }
    },
    error => {
        console.log('err' + error)// for debug
        thispoint.$message({
            message: error.msg,
            type: 'error',
            duration: 3 * 1000
        })
        return Promise.reject(error)
    }
)


function logout(){
    let storage = new Storage();
    storage.removeItem("userinfo");
    storage.removeItem("routeMap");
    service.get("/user/logout");
}

function getPublicKey(){
    let storage = new Storage();
    service({
        method: 'get',
        url: '/user/publicKey',
    }).then(response=>{
        storage.setItem({
            name: "publicKey",
            value: response.data
        })
    })
}



