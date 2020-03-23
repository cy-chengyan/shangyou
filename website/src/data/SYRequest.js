
import {API_URL} from "../Constant";

function request({api, data, succ, failed, completed}) {
    let url = API_URL + api;
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
            return;
        }

        res.json().then(function(obj) {
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
        });
    }).catch(function(err) {
        if (failed) {
            failed({msg: '网络请求错误:' + err})
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
