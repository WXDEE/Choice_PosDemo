/**
 * Created by Knove on 2017/11/10.
 */
import { combineReducers } from 'redux';
import * as type from '../action/type';

const initialList = {
    theNumber: 0,
    theString:"initial string!",
}
const httpData = (state = initialList, action) => {
    switch (action.type) {
        case type.RECEIVE_DATA:
            return Object.assign({}, state, { theNumber: state.theNumber + 1 });
            break;
        case type.REQUEST_DATA:
            return Object.assign({}, state, { theNumber: state.theNumber - 1 });
            break;
        case type.REQUEST_ORDER:
            return Object.assign({}, state, action.data);
            break;
        case type.REQUEST_FOOD:
            return Object.assign({},state, action.data);
            break;
        case type.POINT_DESK:
            return Object.assign({}, state, {deskNumber:action.deskNumber});
            break;
        case type.INIT_ORDER:
            return Object.assign({}, state, {deskTable:action.deskArray});
            break;
        case type.ADD_FOOD:
            let subEach=deepCopy(state,{});
            subEach.deskTable[action.deskNum].foodArray.push(action.foodDetails)
            return subEach;
            break;
        case type.DELETE_FOOD:
            let subEach1=deepCopy(state,{});

            let index=subEach1.deskTable[action.nowDeskNum].foodArray.length;
            for(let i=index;i>0;i--){
                console.log(subEach1.deskTable[action.nowDeskNum].foodArray[i-1].key);
                if(subEach1.deskTable[action.nowDeskNum].foodArray[i-1].key==action.nowFoodNum){
                    subEach1.deskTable[action.nowDeskNum].foodArray.splice(i-1,1);
                }
            }
            return subEach1;
            break;
        case type.NUMBER_FOOD:

            let subEach2=deepCopy(state,{});
            let index1=subEach2.deskTable[action.nowDeskNum].foodArray.length;
            for(let i=index1;i>0;i--){
                console.log(subEach2.deskTable[action.nowDeskNum].foodArray[i-1].key);
                if(subEach2.deskTable[action.nowDeskNum].foodArray[i-1].key==action.nowFoodNum){
                    subEach2.deskTable[action.nowDeskNum].foodArray[i-1].nowNum=action.newFoodNumber;
                }
            }
            return subEach2;
            break;
        default:
            return state;
    }
};
//实现深复制
function deepCopy(o,c){
    var c = c || {}
    for(var i in o){
        if(typeof o[i] === 'object'){
            //要考虑深复制问题了
            if(o[i].constructor === Array){
                //这是数组
                c[i] =[]
            }else{
                //这是对象
                c[i] = {}
            }
            deepCopy(o[i],c[i])
        }else{
            c[i] = o[i]
        }
    }
    return c
}

export default combineReducers({httpData});
