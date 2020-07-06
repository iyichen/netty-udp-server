// Code generated by protoc-gen-go. DO NOT EDIT.
// source: message.proto

package pb

import (
	fmt "fmt"
	proto "github.com/golang/protobuf/proto"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.ProtoPackageIsVersion3 // please upgrade the proto package

type MessageType int32

const (
	MessageType_OK        MessageType = 0
	MessageType_PING      MessageType = 1
	MessageType_PONG      MessageType = 2
	MessageType_LOGIN     MessageType = 3
	MessageType_LOGOUT    MessageType = 4
	MessageType_BROADCAST MessageType = 6
	MessageType_CHAT      MessageType = 7
)

var MessageType_name = map[int32]string{
	0: "OK",
	1: "PING",
	2: "PONG",
	3: "LOGIN",
	4: "LOGOUT",
	6: "BROADCAST",
	7: "CHAT",
}

var MessageType_value = map[string]int32{
	"OK":        0,
	"PING":      1,
	"PONG":      2,
	"LOGIN":     3,
	"LOGOUT":    4,
	"BROADCAST": 6,
	"CHAT":      7,
}

func (x MessageType) String() string {
	return proto.EnumName(MessageType_name, int32(x))
}

func (MessageType) EnumDescriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{0}
}

// 心跳Ping-1
type Ping struct {
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Ping) Reset()         { *m = Ping{} }
func (m *Ping) String() string { return proto.CompactTextString(m) }
func (*Ping) ProtoMessage()    {}
func (*Ping) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{0}
}

func (m *Ping) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Ping.Unmarshal(m, b)
}
func (m *Ping) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Ping.Marshal(b, m, deterministic)
}
func (m *Ping) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Ping.Merge(m, src)
}
func (m *Ping) XXX_Size() int {
	return xxx_messageInfo_Ping.Size(m)
}
func (m *Ping) XXX_DiscardUnknown() {
	xxx_messageInfo_Ping.DiscardUnknown(m)
}

var xxx_messageInfo_Ping proto.InternalMessageInfo

// 心跳Pong-2
type Pong struct {
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Pong) Reset()         { *m = Pong{} }
func (m *Pong) String() string { return proto.CompactTextString(m) }
func (*Pong) ProtoMessage()    {}
func (*Pong) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{1}
}

func (m *Pong) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Pong.Unmarshal(m, b)
}
func (m *Pong) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Pong.Marshal(b, m, deterministic)
}
func (m *Pong) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Pong.Merge(m, src)
}
func (m *Pong) XXX_Size() int {
	return xxx_messageInfo_Pong.Size(m)
}
func (m *Pong) XXX_DiscardUnknown() {
	xxx_messageInfo_Pong.DiscardUnknown(m)
}

var xxx_messageInfo_Pong proto.InternalMessageInfo

type Login struct {
	Name                 string   `protobuf:"bytes,1,opt,name=name,proto3" json:"name,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Login) Reset()         { *m = Login{} }
func (m *Login) String() string { return proto.CompactTextString(m) }
func (*Login) ProtoMessage()    {}
func (*Login) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{2}
}

func (m *Login) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Login.Unmarshal(m, b)
}
func (m *Login) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Login.Marshal(b, m, deterministic)
}
func (m *Login) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Login.Merge(m, src)
}
func (m *Login) XXX_Size() int {
	return xxx_messageInfo_Login.Size(m)
}
func (m *Login) XXX_DiscardUnknown() {
	xxx_messageInfo_Login.DiscardUnknown(m)
}

var xxx_messageInfo_Login proto.InternalMessageInfo

func (m *Login) GetName() string {
	if m != nil {
		return m.Name
	}
	return ""
}

type Logout struct {
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Logout) Reset()         { *m = Logout{} }
func (m *Logout) String() string { return proto.CompactTextString(m) }
func (*Logout) ProtoMessage()    {}
func (*Logout) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{3}
}

func (m *Logout) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Logout.Unmarshal(m, b)
}
func (m *Logout) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Logout.Marshal(b, m, deterministic)
}
func (m *Logout) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Logout.Merge(m, src)
}
func (m *Logout) XXX_Size() int {
	return xxx_messageInfo_Logout.Size(m)
}
func (m *Logout) XXX_DiscardUnknown() {
	xxx_messageInfo_Logout.DiscardUnknown(m)
}

var xxx_messageInfo_Logout proto.InternalMessageInfo

type Chat struct {
	Content              string   `protobuf:"bytes,1,opt,name=content,proto3" json:"content,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Chat) Reset()         { *m = Chat{} }
func (m *Chat) String() string { return proto.CompactTextString(m) }
func (*Chat) ProtoMessage()    {}
func (*Chat) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{4}
}

func (m *Chat) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Chat.Unmarshal(m, b)
}
func (m *Chat) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Chat.Marshal(b, m, deterministic)
}
func (m *Chat) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Chat.Merge(m, src)
}
func (m *Chat) XXX_Size() int {
	return xxx_messageInfo_Chat.Size(m)
}
func (m *Chat) XXX_DiscardUnknown() {
	xxx_messageInfo_Chat.DiscardUnknown(m)
}

var xxx_messageInfo_Chat proto.InternalMessageInfo

func (m *Chat) GetContent() string {
	if m != nil {
		return m.Content
	}
	return ""
}

type Broadcast struct {
	Content              string   `protobuf:"bytes,1,opt,name=content,proto3" json:"content,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Broadcast) Reset()         { *m = Broadcast{} }
func (m *Broadcast) String() string { return proto.CompactTextString(m) }
func (*Broadcast) ProtoMessage()    {}
func (*Broadcast) Descriptor() ([]byte, []int) {
	return fileDescriptor_33c57e4bae7b9afd, []int{5}
}

func (m *Broadcast) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Broadcast.Unmarshal(m, b)
}
func (m *Broadcast) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Broadcast.Marshal(b, m, deterministic)
}
func (m *Broadcast) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Broadcast.Merge(m, src)
}
func (m *Broadcast) XXX_Size() int {
	return xxx_messageInfo_Broadcast.Size(m)
}
func (m *Broadcast) XXX_DiscardUnknown() {
	xxx_messageInfo_Broadcast.DiscardUnknown(m)
}

var xxx_messageInfo_Broadcast proto.InternalMessageInfo

func (m *Broadcast) GetContent() string {
	if m != nil {
		return m.Content
	}
	return ""
}

func init() {
	proto.RegisterEnum("pb.MessageType", MessageType_name, MessageType_value)
	proto.RegisterType((*Ping)(nil), "pb.Ping")
	proto.RegisterType((*Pong)(nil), "pb.Pong")
	proto.RegisterType((*Login)(nil), "pb.Login")
	proto.RegisterType((*Logout)(nil), "pb.Logout")
	proto.RegisterType((*Chat)(nil), "pb.Chat")
	proto.RegisterType((*Broadcast)(nil), "pb.Broadcast")
}

func init() {
	proto.RegisterFile("message.proto", fileDescriptor_33c57e4bae7b9afd)
}

var fileDescriptor_33c57e4bae7b9afd = []byte{
	// 213 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x7c, 0x8f, 0xb1, 0x4b, 0x03, 0x31,
	0x18, 0xc5, 0xbd, 0x33, 0x4d, 0x9b, 0x4f, 0x0a, 0xe1, 0x9b, 0x0a, 0x2e, 0x25, 0x20, 0x88, 0x83,
	0x8b, 0x7f, 0xc1, 0xf5, 0x84, 0x58, 0x8c, 0x97, 0x52, 0xe3, 0xe0, 0x98, 0xd6, 0x10, 0x3b, 0x5c,
	0xbe, 0xe3, 0x2e, 0x0e, 0xfe, 0xf7, 0x62, 0xd4, 0xd5, 0xe9, 0xfd, 0x1e, 0xfc, 0x86, 0xf7, 0x60,
	0xd9, 0x87, 0x69, 0xf2, 0x31, 0xdc, 0x0e, 0x23, 0x65, 0xc2, 0x7a, 0x38, 0x28, 0x0e, 0x6c, 0x77,
	0x4a, 0xb1, 0x24, 0xa5, 0xa8, 0x2e, 0x61, 0x66, 0x28, 0x9e, 0x12, 0x22, 0xb0, 0xe4, 0xfb, 0xb0,
	0xaa, 0xd6, 0xd5, 0xb5, 0xd8, 0x17, 0x56, 0x0b, 0xe0, 0x86, 0x22, 0x7d, 0x64, 0xb5, 0x06, 0xd6,
	0xbe, 0xfb, 0x8c, 0x2b, 0x98, 0x1f, 0x29, 0xe5, 0x90, 0xf2, 0xaf, 0xf8, 0x57, 0xd5, 0x15, 0x88,
	0xcd, 0x48, 0xfe, 0xed, 0xe8, 0xa7, 0x7f, 0xb4, 0x9b, 0x57, 0xb8, 0x78, 0xfa, 0x19, 0xe5, 0x3e,
	0x87, 0x80, 0x1c, 0x6a, 0xfb, 0x28, 0xcf, 0x70, 0x01, 0x6c, 0xb7, 0xed, 0xb4, 0xac, 0x0a, 0xd9,
	0x4e, 0xcb, 0x1a, 0x05, 0xcc, 0x8c, 0xd5, 0xdb, 0x4e, 0x9e, 0x23, 0x00, 0x37, 0x56, 0xdb, 0x17,
	0x27, 0x19, 0x2e, 0x41, 0x6c, 0xf6, 0xb6, 0xb9, 0x6f, 0x9b, 0x67, 0x27, 0xf9, 0xb7, 0xdf, 0x3e,
	0x34, 0x4e, 0xce, 0x0f, 0xbc, 0xbc, 0xbc, 0xfb, 0x0a, 0x00, 0x00, 0xff, 0xff, 0xfc, 0x8c, 0x31,
	0x63, 0xf6, 0x00, 0x00, 0x00,
}
