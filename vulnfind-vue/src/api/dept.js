import axios from 'axios'

export const findDeptAndCount = () => {
  return axios({
    url: '/department/findDeptAndCount',
    method: 'get',
    headers: {
      Authorization: localStorage.getItem('token')
    }
  })
}
