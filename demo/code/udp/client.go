package main

import (
    "bufio"
    "fmt"
    "github.com/golang/protobuf/proto"
    "inaction/socket/udp/pack"
    "inaction/socket/udp/pb"
    "net"
    "os"
)

func main() {
    conn, err := net.Dial("udp", "127.0.0.1:11201")
    if err != nil {
        fmt.Println("client start error.", err)
        return
    }
    
    reader := bufio.NewReader(os.Stdin)
    
    fmt.Print("Enter You Name：")
    name, _, err := reader.ReadLine()
    if err != nil {
        fmt.Println(err)
        return
    }
    
    login := &pb.Login{
        Name: string(name),
    }
    
    data, err := proto.Marshal(login)
    if err != nil {
        fmt.Println(err)
        return
    }
    
    dp := pack.NewDataPack()
    buf, err := dp.Pack(pack.NewPacket(3, data))
    
    _, err = conn.Write(buf)
    if err != nil {
        fmt.Println(err)
    }
    
    go readMessage(conn)
    
    for ; ; {
        data, _, _ := reader.ReadLine()
        
        if string(data) == "quit" {
            fmt.Println("Quit Now ...")
            
            logout := &pb.Logout{
            }
            
            data, err := proto.Marshal(logout)
            if err != nil {
                fmt.Println(err)
                return
            }
            dp := pack.NewDataPack()
            buf, err := dp.Pack(pack.NewPacket(4, data))
            
            _, err = conn.Write(buf)
            if err != nil {
                fmt.Println(err)
            }
            
            os.Exit(0)
        }
        
        chat := &pb.Chat{
            Content: string(data),
        }
        
        data, err := proto.Marshal(chat)
        if err != nil {
            fmt.Println(err)
            break
        }
        
        dp := pack.NewDataPack()
        buf, err := dp.Pack(pack.NewPacket(7, data))
        
        _, err = conn.Write(buf)
        if err != nil {
            fmt.Println(err)
        }
    }
}

func readMessage(conn net.Conn) {
    for {
        dp := pack.NewDataPack()
        
        message, err := dp.UnpackConnection(conn)
        if err != nil {
            fmt.Println("Recv message error.", err)
            break
        }
        
        switch message.Id {
        case 6:
            newdata := &pb.Broadcast{}
            err = proto.Unmarshal(message.Data, newdata)
            if err != nil {
                fmt.Println(err)
                continue
            }
            
            fmt.Println(newdata.Content)
        case 1: // ping
            newdata := &pb.Ping{}
            err = proto.Unmarshal(message.Data, newdata)
            if err != nil {
                fmt.Println(err)
                continue
            }
            
            pong := &pb.Pong{}
            data, err := proto.Marshal(pong)
            if err != nil {
                fmt.Println(err)
                break
            }
            dp := pack.NewDataPack()
            
            buf, err := dp.Pack(pack.NewPacket(2, data))
            _, err = conn.Write(buf)
            break
        }
        
    }
}
