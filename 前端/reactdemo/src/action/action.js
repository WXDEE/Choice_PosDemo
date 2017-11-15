/**
 * Created by Knove on 2017/11/10.
 */
import * as type from '../action/type';
//订单页面的初始化
export function orderInit(){
    return (dispatch)=>{
        fetch("http://localhost:8080/orders/list",{
            method: 'POST',
            mode: 'cors',
            cache: 'default'
        })
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
        fetch("http://localhost:8080/dish/list",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "sdDate=undefined",
        })
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

    let deskSum=14;   //获取到桌子数量

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

//查询菜品
export function dataSearch(data) {
    /*
    解决跨域问题
    */
    console.log(data);
    return dispatch=>{
        fetch("http://localhost:8080/dish/list",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "dName="+data.dName+"&sdDate="+data.sdDate+"&edDate="+data.edDate,
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                dispatch(foodInitDo(json));
                console.log(json);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//查询订单
export function orderSearch(data) {
    console.log(data);
    return dispatch=>{
        fetch("http://localhost:8080/orders/list",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "oNum="+data.dName+"&sDate="+data.sdDate+"&eDate="+data.edDate,

        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                dispatch(orderInitDo(json));
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//增加菜品
export function addFood(data) {
    console.log(data);
    return dispatch=>{
        fetch("http://localhost:8080/dish/add",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "dCn="+data.dCn+"&dCount="+data.dCount+"&dMaterial="+data.dMaterial+"&dName="+data.dName+"&dPrice="+data.dPrice
            +"&dRemark="+data.dRemark+"&dcId="+data.dcId,
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
                foodInit()(dispatch);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//修改菜品
export function uploadFood(data) {
    console.log(data);
    return dispatch=>{
        fetch("http://localhost:8080/dish/update",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "dMaterial ="+data.dMaterial+"&dPrice="+data.dPrice
            +"&dRemark ="+data.dRemark+"&dCount="+data.dCount,
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
                foodInit()(dispatch);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//删除菜品
export function deleteFood(id) {
    return dispatch=>{
        fetch("http://localhost:8080/dish/delete",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "id="+id ,

        }).then((response)=>{
            if(response.status!==200){
                console.log("删除数据出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
                foodInit()(dispatch);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
//查询桌子
export function deskSearch() {
    return dispatch=>{
        fetch("http://localhost:8080/desk/list",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default'
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
               console.log(json);
                dispatch(deskAllToStore(json));
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
export const deskAllToStore=(data)=>{
    return {type: type.REQUEST_DESK,data:data}
}

//提交订单
export function pushOrder(data) {
    console.log(JSON.stringify(data));
    return dispatch=>{
        fetch("http://localhost:8080/orders/add",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body: "data="+JSON.stringify(data)
        }).then((response)=>{
            if(response.status!==200){
                console.log("存入数据时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
                dispatch(orderStateToStore(json));
                deskSearch()(dispatch);
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
export const orderStateToStore=(data)=>{
    return {type: type.PUSH_ORDER,data}
}
//查看订单明细
export function seeOrderDetails(id) {
    return dispatch=>{
        fetch("http://localhost:8080/orderitem/ordersId",{
            method:'POST',
            headers: {
                'Accept': 'application/json, text/javascript, */*; q=0.01',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            mode: 'cors',
            credentials: 'credentials',
            cache: 'default',
            body:'id='+id,
        }).then((response)=>{
            if(response.status!==200){
                console.log("查看订单明细时出错，状态码为"+response.status);
                return ;
            }
            response.json().then(json=>{
                console.log(json);
                dispatch(orderDetails(json));
            })
        }).catch(err=>{
            console.log("fetch错误"+err);
        })
    }
}
export const orderDetails=(data)=>{
    return {type: type.ORDER_DETAILS,data:data}
}
