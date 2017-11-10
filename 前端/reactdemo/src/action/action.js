import * as type from '../action/type';

export function addNum(){
  return (dispatch)=>{
    dispatch(addNumWithStore());
    //你可以在此处向服务器请求数据
  }
}
export const addNumWithStore=()=>{
    return {type: type.REQUEST_DATA}
}
