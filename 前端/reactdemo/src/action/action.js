/**
 * Created by Knove on 2017/11/10.
 */
import * as type from '../action/type';
export function orderInit(){
    return (dispatch)=>{
        dispatch(orderInitDo());
        //你可以在此处向服务器请求数据
    }
}
export const orderInitDo=()=>{
    return {type: type.REQUEST_ORDER}
}

export function foodInit(){
    return (dispatch)=>{
        dispatch(foodInitDo());
        //你可以在此处向服务器请求数据
    }
}
export const foodInitDo=()=>{
    return {type: type.REQUEST_FOOD}
}

export function addNum(){
  return (dispatch)=>{
    dispatch(addNumWithStore());
    //你可以在此处向服务器请求数据
  }
}
export const addNumWithStore=()=>{
    return {type: type.RECEIVE_DATA}
}
