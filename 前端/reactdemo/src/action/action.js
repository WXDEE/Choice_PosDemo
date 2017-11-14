/**
 * Created by Knove on 2017/11/10.
 */
import * as type from '../action/type';
//订单页面的初始化
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
//菜品页面的初始化
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
//测试
export function addNum(){
  return (dispatch)=>{

    dispatch(addNumWithStore());
  }
}
export const addNumWithStore=()=>{
    return {type: type.RECEIVE_DATA}
}
//指定桌号的action
export function pointNowDesk(number){
    return (dispatch)=>{
        dispatch(pointNowDesktoStore(number));
    }
}
export const pointNowDesktoStore=(number)=>{
    return {type: type.POINT_DESK,deskNumber:number}
}
//初始化服务员订餐页面
export function initOrdering(){
return (dispatch)=>{
    let deskSum=16;   //获取到桌子数量

    let deskArray=new Array();  //新建桌子的集合
    for(let i=0;i<deskSum+1;i++){
        deskArray.push({
                num:i,
                foodArray:[],
        });
    }
    console.log(deskArray[0].foodArray);
    dispatch(initOrderingtoStore(deskArray));
}
}
export const initOrderingtoStore=(deskArray)=>{
    return {type: type.INIT_ORDER,deskArray:deskArray}
}
//增加菜品到右侧信息栏

export function addFoodDetails(index,nowDeskNum,nowFoodNum,foodName,foodPrice,foodNum){
    return (dispatch)=>{
          let deskNum=nowDeskNum;
           let foodDetails=   {key: nowFoodNum+1,
                    FoodID:index,
                    FoodName: foodName,
                    Num:foodNum,
                    Price: foodPrice,
                     nowNum:1
                };

        dispatch(addFoodDetailstoStore(foodDetails,deskNum));
    }
}
export const addFoodDetailstoStore=(foodDetails,deskNum)=>{
    return {type: type.ADD_FOOD,foodDetails:foodDetails,deskNum:deskNum}
}
//删除菜品从右侧信息栏

export function deleteFoodDetails(nowDeskNum,nowFoodNum){
    return (dispatch)=>{
        dispatch(deleteFoodDetailstoStore(nowDeskNum,nowFoodNum));
    }
}
export const deleteFoodDetailstoStore=(nowDeskNum,nowFoodNum)=>{
    return {type: type.DELETE_FOOD,nowDeskNum:nowDeskNum,nowFoodNum:nowFoodNum}
}
//修改数量
export function numberFoodDetails(nowDeskNum,nowFoodNum,newFoodNumber){
    return (dispatch)=>{
        dispatch(numberFoodDetailstoStore(nowDeskNum,nowFoodNum,newFoodNumber));
    }
}
export const numberFoodDetailstoStore=(nowDeskNum,nowFoodNum,newFoodNumber)=>{
    return {type: type.NUMBER_FOOD,nowDeskNum:nowDeskNum,nowFoodNum:nowFoodNum,newFoodNumber:newFoodNumber}
}

//查询数据
export function dataSearch(data) {
    return dispatch=>{
        fetch("http://localhost:3000/data_try.json",{
            method:'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body:data,
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//查询数据
export function orderSearch(data) {
    return dispatch=>{
        fetch("http://localhost:3000/data_try.json",{
            method:'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body:data,
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}

