package pack

type Packet struct {
    Id   int32
    Data []byte
}

func NewPacket(id int32, data []byte) *Packet {
    return &Packet{
        Id:   id,
        Data: data,
    }
}
