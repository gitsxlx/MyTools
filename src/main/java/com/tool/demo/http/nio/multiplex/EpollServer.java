package com.tool.demo.http.nio.multiplex;

/**
 * EpollServer
 *
 * 基本的IO编程过程（包括网络IO和文件IO）是，打开文件描述符（windows是handler，java是stream或channel），多路捕获（Multiplexe，即select和poll和epoll）IO可读写的状态，而后可以读写的文件描述符进行IO读写
 *
 * select：
 *  无差别轮询所有流，单个进程可监视的fd数量被限制，即能监听端口的大小有限。时间复杂度O(n)。
 * poll：
 *  忙轮询，poll本质上和select没有区别，它将用户传入的数组拷贝到内核空间，然后查询每个fd对应的设备状态，但是它没有最大连接数的限制，原因是它是基于链表来存储的。时间复杂度O(n)
 * epoll：
 *  epoll可以理解为event poll，所以我们说epoll实际上是事件驱动（每个事件关联上fd）的，此时我们对这些流的操作都是有意义的。（复杂度降低到了O(1)）。
 *  epoll 是Linux下，高效的多路复用技术，也是Linux下高性能网络服务器的关键技术。
 *  分为三个函数，第一个函数创建一个session类似的东西，第二函数告诉内核维持这个session，并把属于session内的fd传给内核，第三个函数epoll_wait是真正的监控多个文件描述符函数，只需要告诉内核，我在等待哪个session，而session内的fd，内核早就分析过了，不再在每次epoll调用的时候分析，这就节省了内核大部分工作。
 *  这样每次调用epoll，内核不再重新扫描fd数组，因为我们维持了session。
 * @author WangChao
 * @date 2020/05/05
 */
public class EpollServer {

}
