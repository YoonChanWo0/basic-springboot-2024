// Hopk 함수 사용
import React, { useState, useEffect } from 'react';

import axios from 'axios'; // rest api 호출 핵심!!

function BoardList(){ // 객체를 만드는 함수
    // 변수 선언
    const [boardList, setBoardList] = useState([]); // 배열값을 받아서 상태를 저장하기 때문에 []

    console.log("시작");
    
    // 함수 선언
    // 제일 중요!!!
    const getBoardList = async () => {
        console.log("restapi 호출");
        var pageString = 'page=0';
        const resp = (await axios.get("http://localhost:8080/api/board/list/free?" + pageString)).data;
        setBoardList(resp); // boardList 변수에 담는 작업
        console.log(resp);
    } 

    useEffect(()=> {
         getBoardList();
    }, []);


    return(
        <div className="container mx-3">
        <table className='table'>
            <thead className='table-dark'>
                <tr className='text-center'>
                    <th>번호</th>
                    <th style={{width: '50%'}}>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                {/*반복으로 들어갈 부분 */}
                {boardList.map((board) =>(
                    <tr className='text-center' key={board.bno}>
                        <td>{board.bno}</td>
                        <td className='test-start'>{board.title}</td>
                        <td>{board.writer}</td>
                        <td>{board.hit}</td>
                        <td>{board.createDate}</td>
                    </tr>
                ))}
                
                
            </tbody>
        </table>
    </div>
    );
}

export default BoardList;