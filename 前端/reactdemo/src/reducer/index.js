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
            return Object.assign({}, state, action.date);
            break;
        case type.REQUEST_FOOD:
            return Object.assign({},state,{foodTable:[{
                key: 1,
                name: '麻婆豆腐',
                price: '￥10',
                num: 0,
                date: "2017-11-11",
                initial: 'MPDF',
                type: '素菜',
                material: '麻婆、豆腐',
                remark: '对辣椒过敏者慎食'
            },{
                key: 2,
                name: '麻婆豆腐',
                price: '￥10',
                num: 0,
                date: "2017-11-11",
                initial: 'MPDF',
                type: '素菜',
                material: '麻婆、豆腐',
                remark: '对辣椒过敏者慎食'
            },{
                key: 3,
                name: '麻婆豆腐',
                price: '￥10',
                num: 0,
                date: "2017-11-11",
                initial: 'MPDF',
                type: '素菜',
                material: '麻婆、豆腐',
                remark: '对辣椒过敏者慎食'
            },{
                key: 4,
                name: '麻婆豆腐',
                price: '￥10',
                num: 0,
                date: "2017-11-11",
                initial: 'MPDF',
                type: '素菜',
                material: '麻婆、豆腐',
                remark: '对辣椒过敏者慎食'
            }]});
            break;
        default:
            return state;
    }
};


export default combineReducers({httpData});
