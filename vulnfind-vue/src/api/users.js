import axios from 'axios'

export const findUserList = (current, size, userVO) => {
  return axios({
    url: '/user/findUserPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO,
    headers: {
      Authorization: localStorage.getItem('token')
    }
  })
}

export const deleteImgFile = (file) => {
  return axios({
    url: '/deleteImgFile',
    method: 'post',
    params: {
      file
    },
    headers: {
      Authorization: localStorage.getItem('token')
    }
  })
}

export const saveUser = (user) => {
  return axios({
    url: '/user/addUser',
    method: 'post',
    data: user,
    headers: {
      Authorization: localStorage.getItem('token')
    }
  })
}
export const delUser = (id) => {
  return axios({
    url: '/user/deleteUser',
    method: 'post',
    data: id,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      Authorization: localStorage.getItem('token')
    }
  })
}
