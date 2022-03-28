import React, { useState } from 'react';
import { useEffect } from 'react';
import './Memberinfo.css'

export default function Memberinfo(props) {
  // const party = props.part
  // console.log(props.part)
  const [party, setParty] = useState({});
  const [namepjy, setNamepjy] = useState('');
  const [namekbj, setNamekbj] = useState('');
  const [namekse, setNamekse] = useState('');
  const [namelns, setNamelns] = useState('');
  const [namepsh, setNamepsh] = useState('');
  const [nameyjj, setNameyjj] = useState('');
  const [nameyoonbal, setNameyoonbal] = useState('');
  // const items = [party
  //   // {
  //   //   name: "모자 별 시간",
  //   //   description: "원그래프!",
  //   //   graph: <Piechart hatTime={hatTime}></Piechart>,
  //   // },
  //   // {
  //   //   name: "총 회의 횟수 : n회",
  //   //   description: "꺾은선 그래프!",
  //   //   graph: LineChart(),
  //   // },
  // ]
  useEffect(() => {
    if(props.part)
    setParty(props.part)
  },[props.part])

  // useEffect(() => {
  //   if(party)
  //   console.log(party.윤발)
  //   console.log(party.박주윤)
  // },[party])
  useEffect(() => {
    if(party['박주윤'])
    setNamepjy('박주윤')
  }, [party['박주윤']])
  
  useEffect(() => {
    if(party['Yu J'])
    setNameyjj('Yu J')
  }, [party['Yu J']])

  useEffect(() => {
    if(party['박상훈'])
    setNamepsh('박상훈')
  }, [party['박상훈']])

  useEffect(() => {
    if(party['ᄋᄂᄉᄂᄋ'])
    setNamelns('ᄋᄂᄉᄂᄋ')
  }, [party['ᄋᄂᄉᄂᄋ']])
  
  useEffect(() => {
    if(party['stacy kim'])
    setNamekse('stacy kim')
  }, [party['stacy kim']])
  
  useEffect(() => {
    if(party['Byeongwan Kim'])
    setNamekbj('Byeongwan Kim')
  }, [party['Byeongwan Kim']])
  useEffect(() => {
    if(party.윤발)
    setNameyoonbal('윤발')
  }, [party.윤발])


  // function Item(props)
  // {
  //   return (
  //     <div>
  //       {/* <h2 className='font-color'>{props.item.name}</h2>
  //       <p className='font-color'>{props.item.description}</p>
  //       {props.item.graph} */}

  //       <div className="CheckButton">
  //         Check it out!
  //       </div>
  //     </div>
  //     )
  // }
  
  return (
    <div>
      <div className="member">
        <h4>Memberinfo</h4>
         <p>
           {namekbj}
        </p>
         <p>
           {nameyjj}
        </p>
         <p>
           {namepsh}
        </p>
         <p>
           {namelns}
        </p>
         <p>
           {namekse}
        </p>
         <p>
           {namepjy}
        </p>
         <p>
           {nameyoonbal}
        </p>
      
      </div>

        {/* <div>
        {
          items.map( (item, i) => <div key={i} item={item} /> )
        }
      </div> */}
    </div>
  );
};

