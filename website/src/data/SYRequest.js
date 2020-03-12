
const apiUrl = 'https://shangyou.life/mp';

function request({api, data, succ, failed, completed}) {
    let url = apiUrl + api;
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Accept": 'application/json',
        },
        body: JSON.stringify({
            data,
            login_info: {
                login_at: 0,
                token: null,
                uid: null,
            }
        }),
    }).then(function(res) {
        console.debug(res);
        if (res.status !== 200) {
            if (failed)
                failed({msg:'网络请求失败',});
        }
        else
            return res.text();
    }).then(function(body) {
        let obj = JSON.parse(body);
        let code = obj.code;
        if (code !== 0) {
            if (failed) {
                failed({msg: obj.msg});
            }
        } else {
            if (succ) {
                succ(obj.data);
            }
        }
    }).finally(function(res) {
        console.debug("finally");
        if (completed)
            completed(res);
    })
}

/// 获取邮票列表
function stampList(offset, size, type, year, {succ, failed, completed}) {
    request({
        api: '/v1/stamp/list',
        data: {
            offset,
            size,
            type,
            year,
        },
        succ,
        failed,
        completed,
    })
}

export {
    stampList,
};
