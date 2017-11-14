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
export const orderInitDo=(data)=>{
    return {type: type.REQUEST_ORDER,data:data}
}

export function foodInit(){
    return (dispatch)=>{
        fetch("http://localhost:3000/data_food.json")
            .then(response=>{
                if(response.status!==200){
                    console.log("和后台交互时候出现问题，状态码为："+response.status);
                    return;
                }
                response.json().then(data=>{
                    dispatch(foodInitDo(data));
                });
            }).catch(function (err) {
                console.log("fetch错误："+err);
        });
    }
}
export const foodInitDo=(data)=>{
    return {type: type.REQUEST_FOOD,data:data};
}

export function addNum(){
  return (dispatch)=>{

    dispatch(addNumWithStore());
  }
}
export const addNumWithStore=()=>{
    return {type: type.RECEIVE_DATA}
}
