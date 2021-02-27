import axios from "axios";

export const findUserList= (current,size) => {
    return axios({
        url: "/user/findUserList",
        method: 'get',
        params: {
            current: current,
            size: size
        },
        headers:{
            "Authorization": localStorage.getItem("token")
        }
    })
}
