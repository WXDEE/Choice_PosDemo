/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import CRouter from './routes';
import thunk from 'redux-thunk';
import { createStore, applyMiddleware } from 'redux';
import reducer from './reducer';
import { composeWithDevTools } from 'redux-devtools-extension'
import { Provider } from 'react-redux';
import './style/index.less';

// redux 注入操作
const middleware = [thunk];
const store = createStore(reducer,composeWithDevTools(),applyMiddleware(...middleware));
console.log(store.getState());
let ScreenHeight=document.body.clientHeight;
console.log(ScreenHeight);
ReactDOM.render(
    <Provider store={store}>
        <CRouter store={store}  />
    </Provider>
    , document.getElementById('root'));

