import { Avatar, Card, CardContent, CardHeader, Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material"
import { useEffect, useState } from "react"
import { useLocation } from "react-router-dom"
import { getAttendersByMeetingId, getMeesagesByMeetingId, getMessages } from "../../util/APIUtils"

export function MeetingResult() {
  const { state } = useLocation()
  const meeting = state.meeting
  const [ attenders, setAttenders ] = useState([])
  const [ message, setMessage ] = useState("")
  const startTime = meeting.startTime
  const endTime = meeting.endTime
  
  function getYear(time) { return Number(time.slice(0, 4))}
  function getMonth(time) { return Number(time.slice(5, 7))}
  function getDay(time) { return Number(time.slice(8, 10))}
  function getHour(time) { return (time.slice(11, 13))}
  function getMinute(time) { return (time.slice(14, 16))}
  function getColor(color) {
    switch (color) {
      case 'RED':
        return '#CC0000'
      case 'GREEN':
        return '#66CC00'
      case 'BLUE':
        return '#0000CC'
      case 'WHITE':
        return '#EBEBEB'
      case 'BLACK':
        return '#424242'
      case 'YELLOW':
        return '#FFFF66'
      default:
        return '#7b7cc2'
    }
  }

  useEffect(() => {
    getAttendersByMeetingId(meeting.id)
    .then(response => {
      setAttenders(response.data.map(attender => {
        const color = getColor(attender.hat_color)
        return (
          <CardHeader
            key={attender.id}
            avatar = {
              <Avatar
                alt={attender.user.name}
                src={attender.user.imageUrl}
              />}
            title={attender.user.name}
            subheader={attender.user.email}
            sx = {{
              color: color
            }}
          />
        )}
    ))}).catch(error => {
      console.log(error)
    })
  }, [meeting])
  
  useEffect(() => {
    getMeesagesByMeetingId(meeting.id)
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
  }, [meeting])

  return (
    <Card
      sx={{
        mt: 10,
        mx: 'auto',
        p: 5,
        width: '70%',
      }}
    >
      <CardHeader title={meeting.topic}/>
      <CardContent>
        <Typography>
          회의 종류 : {meeting.meetingType ? "육색모자" : "일반" } 
        </Typography>
        <br/>
        <Typography>
          회의 일자 : {getYear(startTime)}년 {getMonth(startTime)}월 {getDay(startTime)}일
        </Typography>
        <br/>
        <Typography>
          시작 시간 : {getHour(startTime)}:{getMinute(startTime)}
        </Typography>
        <br/>
        <Typography>
          종료 시간 : {getHour(endTime)}:{getMinute(endTime)}
        </Typography>
        <br/>
        <Typography>
          참여자
        </Typography>
        {attenders}
        <br/>
        <Typography>
          아이디어 보드
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
        </Typography>
      </CardContent>
    </Card>
  )
}
