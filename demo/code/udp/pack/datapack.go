package pack

import (
    "bytes"
    "encoding/binary"
    "fmt"
    "io/ioutil"
    "net"
)

type DataPack struct {
}

func NewDataPack() *DataPack {
    return &DataPack{}
}

func (dp *DataPack) GetHeadLen() int32 {
    return 4
}

func (dp *DataPack) Pack(packet *Packet) ([]byte, error) {
    dataBuf := bytes.NewBuffer([]byte{})
    
    if err := binary.Write(dataBuf, binary.LittleEndian, packet.Id); err != nil {
        fmt.Println("[DataPack] pack data id error.", err)
        return nil, err
    }
    
    if err := binary.Write(dataBuf, binary.LittleEndian, packet.Data); err != nil {
        fmt.Println("[DataPack] unpack data error.", err)
        return nil, err
    }
    
    return dataBuf.Bytes(), nil
}

func (dp *DataPack) unpackHead(dataBuf *bytes.Reader) (*Packet, error) {
    
    message := &Packet{}
    
    if err := binary.Read(dataBuf, binary.LittleEndian, &message.Id); err != nil {
        fmt.Println("[DataPack] unpack data id error.", err)
        return nil, err
    }
    
    return message, nil
}

func (dp *DataPack) UnpackData(data []byte) (*Packet, error) {
    
    dataBuf := bytes.NewReader(data)
    
    message, err := dp.unpackHead(dataBuf)
    if err != nil {
        fmt.Println("[DataPack] unpack data head error.", err)
        return nil, err
    }
    
    message.Data, err = ioutil.ReadAll(dataBuf)
    if err != nil {
        fmt.Println("[DataPack] unpack data error.", err)
        return nil, err
    }
    
    return message, nil
}

func (dp *DataPack) UnpackConnection(conn net.Conn) (*Packet, error) {
    data := make([]byte, 1024)
    n, err := conn.Read(data)
    if err != nil {
        fmt.Println("[DataPack] read conn data error.", err)
        return nil, err
    }
    data = data[0:n]
    
    return dp.UnpackData(data)
}
