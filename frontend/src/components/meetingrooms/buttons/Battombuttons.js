import React, { useEffect, useState } from 'react';
import { IconButton, Button, Modal, Card, Grid, TextField, TableRow, TableCell, TableContainer, Table, TableHead, TableBody } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { ReactComponent as RedHat } from '../img/hat.svg';
import CancelRoundedIcon from '@mui/icons-material/CancelRounded';
import { leaveRoom, mute, sendChat } from '../rooms/MeetingRoom.js';
import { useNavigate } from 'react-router-dom';
import { finishMeeting, getMeesagesByMeetingId } from '../../../util/APIUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMicrophone, faMicrophoneSlash } from "@fortawesome/free-solid-svg-icons";
import { ModalStyle } from '../rooms/ModalStyle';


const theme = createTheme({
  palette: {
    veryPeri: {
      main: '#6667ab'
    }
  }
})

const Battombuttons = (props) => {
  const navigate = useNavigate()

  const [open, setOpen] = useState(false);
  const [open2, setOpen2] = useState(false);
  function handleOpen() {setOpen(true)};
  function handleOpen2() {setOpen2(true)};
  function handleClose() {setOpen(false)};
  function handleClose2() {setOpen2(false)};
  const [message, setMessage] = useState('');

  function getHour(time) { return (time.slice(11, 13))}
  function getMinute(time) { return (time.slice(14, 16))}

  function handleChange({ target: {value} }) {setMessage(value)};

  useEffect(() => {
    getMeesagesByMeetingId(props.meetingId)
    .then(response => {
      setMessage(response.data.map(message => {
        return (
          <TableRow
            key={message.id}
          >
            <TableCell>{message.user.name}</TableCell>
            <TableCell align="center">{message.content}</TableCell>
            <TableCell align="right">{getHour(message.send_time)}:{getMinute(message.send_time)}</TableCell>
          </TableRow>
        )
      }))
    }).catch(error => {
      console.log(error)
    })
  }, [props.meetingId])

  return (
    <ThemeProvider theme={theme}>
      <div className="left-bar">
        { (props.meetingType === 'SIXHAT') &&
        <Button 
          className="hat-button"
          variant="outlined" 
          startIcon={<RedHat width="20"/>} 
          size="large"
          color="veryPeri"
          onClick={() => {
            props.setOpenHatInfo(!props.openHatInfo)
            console.log("openHatInfo is", props.openHatInfo)
            }}
        >
        모자
        </Button>}
      </div>
      <div className="middle-bar">
        <div className="mute-button-box">
          <Button 
            className="mute-button" 
            variant="contained" 
            size="large"
            color="veryPeri"
            onClick={() => {
              props.setMuted(!props.muted);
              mute(props.muted);
              console.log("muted is", props.muted);
            }}
          >
            { !props.muted ? (
              <FontAwesomeIcon icon={faMicrophone} />
              ) : (  
              <FontAwesomeIcon icon={faMicrophoneSlash} />
            )}
          </Button>
        </div>
        <div className="share-screen-button-box">
        </div>
        <div className="exit-button-box">
          <IconButton
            onClick={() => {
              props.setExit(!props.exit);
              console.log("exit is", props.exit);
              leaveRoom();
              if (props.roleType === 'LEADER') {
                finishMeeting({meetingId: props.meetingId})
              }
              navigate(-1);
            }}
          >
            <CancelRoundedIcon className="exit-button"></CancelRoundedIcon>
          </IconButton>
        </div>
      </div>
      <div className="right-bar">
        <Grid container
          spacing={2}
          className="chat-room-button-box"
        >
          <Grid item className="member-button-box">
            <Button
              className="member-button"
              variant="contained"
              size="small"
              color="veryPeri"
              onClick={() => {
                props.setOpenMemberInfo(!props.openMemberInfo)
                console.log("openMemberInfo is", props.openMemberInfo)
                // props.setParti(props.participants)
                console.log(props.participants)
                props.getPart(props.participants)
              }}
            >
              참여자
            </Button>
          </Grid>
          <Grid item>
            <Button
              className="chat-room-button"
              variant="contained"
              size="small"
              color="veryPeri"
              onClick={handleOpen}
            >
              아이디어
            </Button>
            <Modal
              open={open}
              onClose={handleClose}
            >
              <Card
                sx={ModalStyle()}
              >
                <Grid container direction="column" rowSpacing={2}>
                  <Grid item>아이디어 만들기</Grid>
                  <Grid item container
                    component="form"
                    spacing={2}
                    justifyContent="center"
                    alignItems="center"
                    onSubmit={(event => {
                      event.preventDefault()
                      sendChat(message, props.userId, props.meetingId)
                      setMessage("")
                      setOpen(false)
                      window.location.reload()
                    })}
                    >
                    <Grid item>
                      <TextField
                        label="아이디어"
                        name="idea"
                        value={message}
                        size="small"
                        variant="outlined"
                        onChange={handleChange}
                      />
                    </Grid>
                    <Grid item>
                      <Button
                        type="submit"
                        variant="contained"
                      >
                        생성
                      </Button>
                    </Grid>
                  </Grid>
                </Grid>
              </Card>
            </Modal>
          </Grid>
          <Grid item>
            <Button
              className="chat-room-button"
              variant="contained"
              size="small"
              color="veryPeri"
              onClick={handleOpen2}
            >
              보기
            </Button>
            <Modal
              open={open2}
              onClose={handleClose2}
            >
              <Card
                sx={ModalStyle()}
              >
                <Grid container direction="column" rowSpacing={2}>
                  <Grid item>아이디어 보드
                    <TableContainer>
                      <Table>
                        <TableHead>
                          <TableRow>
                            <TableCell>이름</TableCell>
                            <TableCell align="center">아이디어</TableCell>
                            <TableCell align="right">보낸 시간</TableCell>
                          </TableRow>
                        </TableHead>
                        <TableBody>
                          {message}
                        </TableBody>
                      </Table>
                    </TableContainer>
                  </Grid>
                </Grid>
              </Card>
            </Modal>
          </Grid>
        </Grid>
      </div>
    </ThemeProvider>
  );
};

export default Battombuttons;