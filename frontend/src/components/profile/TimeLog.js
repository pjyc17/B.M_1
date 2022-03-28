import { Grid } from '@mui/material';
import { useEffect, useState } from 'react';
import { getAttederWithHat } from '../../util/APIUtils';
import MultiCarouselPage from './MultiCarouselPage';



export default function TimeLog(props) {
  const [userHasMeetingList, setUserHasMeetingList] = useState('');
  const [hatTime, setHatTime] = useState([])
  const speakTime = hatTime.reduce(function add(sum, currValue) {
    return sum + currValue;
  }, 0); // 유저가 발언한 시간의 총 합 -> 초단위로 받음 reduce에 관한 -> 발언시간을 회의 참가 시간으로 변경
  const user = props.user
  const [blackHat, setBlackHat] = useState(0)
  const [yellowHat, setYellowHat] = useState(0)
  const [redHat, setRedHat] = useState(0)
  const [greenHat, setGreenHat] = useState(0)
  const [blueHat, setBlueHat] = useState(0)
  const [whiteHat, setWhiteHat] = useState(0)
  const [hatInfo, setHatInfo] = useState(false)
  // https://react.vlpt.us/basic/16-useEffect.html -> 언마운트 마운트

  useEffect (() => {
    if (user)
    setUserHasMeetingList(user.userHasMeetingList)
  },[user]);
  useEffect(() => {
    if(user)
    getAttederWithHat(user.id)
    .then(response => {
      // console.log(response)
      response.data.map((hat => {
        if (hat.hatColor === 'BLACK')
        setBlackHat(hat.durationTime)
        if (hat.hatColor === 'YELLOW')
        setYellowHat(hat.durationTime)
        if (hat.hatColor === 'RED')
        setRedHat(hat.durationTime)
        if (hat.hatColor === 'GREEN')
        setGreenHat(hat.durationTime)
        if (hat.hatColor === 'BLUE')
        setBlueHat(hat.durationTime)
        if (hat.hatColor === 'WHITE')
        setWhiteHat(hat.durationTime)
      }))
    }).catch(error => {
      console.log(error)
    });
  },[user]);

  // useEffect(() => {
  //   if(speakTime)
  //   setHatInfo(true)
  // },[speakTime]);

  // useEffect(() => {
  //   if(hatInfo)
  //   console.log(2, hatInfo)
  // },[hatInfo])

  useEffect(() => {
    if(yellowHat || redHat || blueHat || blackHat ||  greenHat || whiteHat)
      setHatTime(
        [
          blackHat,
          yellowHat,
          redHat,
          greenHat,
          blueHat,
          whiteHat,
        ]
      )
  },[blackHat, blueHat, greenHat, redHat, whiteHat, yellowHat])
  // useEffect(() => {
  //   if (hatTime)
  //   console.log(hatTime)
  //   // console.log(user)
  // }, [hatTime])

  function calHour(SToH) {
    return parseInt(SToH / 3600);
  }
  function calMin(SToM) {
    return parseInt((SToM % 3600) / 60)
  }
  function calSec(SToS) {
    return parseInt((SToS % 60)%60)
  }
  function numRender() {
    const result = [];
    for (let i = 0; i < hatTime.length; i++) {
      result.push(
      <li key={`모자${i}`}>
        <div className='inLine'>
        <img src={require(`./img/모자${i}.png`)} alt=""></img>
        <p className='font-color'>Time Log : {calHour(hatTime[i]) } h {calMin(hatTime[i])} m {calSec(hatTime[i])}s</p>
        </div>
      </li>);
    }
    return result;
  };
  function didMeeting() {
    if(speakTime) {
      return (
        <Grid item container xs={10} rowSpacing={5}>
          <Grid item xs={12}>
            <Grid className="timeLog" sx={{mt:1}}>
              <h1 className='font-color'>Time Log : {calHour(speakTime)}h {calMin(speakTime)}m {calSec(speakTime)}s</h1>

            </Grid>
          </Grid>
          <Grid item xs={3}>
            <Grid className="Card">
              <h3 className='font-color'> 모자 별 시간 </h3>
              <ul>
                {numRender()}
              </ul>
            </Grid>
          </Grid>
          <Grid item xs={9}>
            <MultiCarouselPage hatTime={hatTime}></MultiCarouselPage>
          </Grid>
        </Grid>
      );
    } else {
      return (
      <Grid className='head-faraway' item container direction='column' xs={10} rowSpacing={5}>
        <Grid className="timeLog" sx={{mt:1}}>
          <br />
          <br />
          <br />
          <h1 className='font-color'>Time Log : {calHour(speakTime)}h {calMin(speakTime)}m {calSec(speakTime)}s</h1>
        </Grid>
        <Grid className="timeLog2" rowSpacing={5} >
          <br />
          <br />
          <br />
          <h2 item className='font-color'>go to meeting~!!!</h2>
          <br />
          <br />
          <br />
          <Grid item className='font-color'>회의를 하게 되면</Grid>
          <Grid item className='font-color'>채워집니다.</Grid>
        </Grid>
      </Grid>
      )
    }
  }
  return (
    // <Grid item container xs={10} rowSpacing={5}>
    //   <Grid item xs={12}>
    //     <Grid className="timeLog" sx={{mt:1}}>
    //       <h1 className='font-color'>Time Log : {calHour(speakTime)}h {calMin(speakTime)}m {calSec(speakTime)}s</h1>

    //     </Grid>
    //   </Grid>
    //   <Grid item xs={3}>
    //     <Grid className="Card">
    //       <h3 className='font-color'> 모자 별 시간 </h3>
    //       <ul>
    //         {numRender()}
    //       </ul>
    //     </Grid>
    //   </Grid>
    //   <Grid item xs={9}>
    //     <MultiCarouselPage hatTime={hatTime}></MultiCarouselPage>
    //   </Grid>
    // </Grid>
    didMeeting()
  )
}