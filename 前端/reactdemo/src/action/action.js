/**
 * Created by Knove on 2017/11/10.
 */
import * as type from '../action/type';
export function orderInit(){
    return (dispatch)=>{
        fetch("http://localhost:3000/data.json")
            .then((response)=>{
                if(response.status!==200){
                    console.log("和后台交互时候出现问题，状态码为："+response.status);
                    return;
                }
                response.json().then((data)=>{
                    dispatch(orderInitDo(data));
                });

        }).catch(function(err){
            console.log("Fetch错误:"+err);
        });

    }
}
export const orderInitDo=(date)=>{
    return {type: type.REQUEST_ORDER,date:date}
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
  }
}
export const addNumWithStore=()=>{
    return {type: type.RECEIVE_DATA}
}
export function pointNowDesk(number){
    return (dispatch)=>{
        dispatch(pointNowDesktoStore(number));
    }
}
export const pointNowDesktoStore=(number)=>{
    return {type: type.POINT_DESK,deskNumber:number}
}

