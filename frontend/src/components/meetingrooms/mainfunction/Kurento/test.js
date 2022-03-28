

export default function sendMessage () {
  let socket = new WebSocket('wss://i6c101.p.ssafy.io/groupcall');
  // socket.onopen = () => socket.send("e");

  socket.onopen = function () {
    console.log("connection server");
    socket.send("sending")
  };
  
  socket.onmessage = function (e) {
      const data = JSON.parse(e.data);
      console.log("data >>", data);
    };
  
    const sendMsg = () => {
      socket.send(
        JSON.stringify({
          message: "zz",
        })
      );
    };
  
  socket.onclose = function (event) {
    if (event.wasClean) {
      console.log("[close] 커넥션 종료됨");
    } else {
      console.log("[close] 커넥션 error");
    }
  };
}

