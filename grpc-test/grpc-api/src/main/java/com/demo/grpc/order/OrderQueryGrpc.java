package com.demo.grpc.order;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * gRPC服务，这是个在线商城的订单查询服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: order.proto")
public final class OrderQueryGrpc {

  private OrderQueryGrpc() {}

  public static final String SERVICE_NAME = "OrderQuery";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.demo.grpc.order.Buyer,
      com.demo.grpc.order.Order> getListOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListOrders",
      requestType = com.demo.grpc.order.Buyer.class,
      responseType = com.demo.grpc.order.Order.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.demo.grpc.order.Buyer,
      com.demo.grpc.order.Order> getListOrdersMethod() {
    io.grpc.MethodDescriptor<com.demo.grpc.order.Buyer, com.demo.grpc.order.Order> getListOrdersMethod;
    if ((getListOrdersMethod = OrderQueryGrpc.getListOrdersMethod) == null) {
      synchronized (OrderQueryGrpc.class) {
        if ((getListOrdersMethod = OrderQueryGrpc.getListOrdersMethod) == null) {
          OrderQueryGrpc.getListOrdersMethod = getListOrdersMethod =
              io.grpc.MethodDescriptor.<com.demo.grpc.order.Buyer, com.demo.grpc.order.Order>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.demo.grpc.order.Buyer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.demo.grpc.order.Order.getDefaultInstance()))
              .setSchemaDescriptor(new OrderQueryMethodDescriptorSupplier("ListOrders"))
              .build();
        }
      }
    }
    return getListOrdersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderQueryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderQueryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderQueryStub>() {
        @java.lang.Override
        public OrderQueryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderQueryStub(channel, callOptions);
        }
      };
    return OrderQueryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderQueryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderQueryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderQueryBlockingStub>() {
        @java.lang.Override
        public OrderQueryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderQueryBlockingStub(channel, callOptions);
        }
      };
    return OrderQueryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderQueryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderQueryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderQueryFutureStub>() {
        @java.lang.Override
        public OrderQueryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderQueryFutureStub(channel, callOptions);
        }
      };
    return OrderQueryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * gRPC服务，这是个在线商城的订单查询服务
   * </pre>
   */
  public static abstract class OrderQueryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 服务端流式：订单列表接口，入参是买家信息，返回订单列表(用stream修饰返回值)
     * </pre>
     */
    public void listOrders(com.demo.grpc.order.Buyer request,
        io.grpc.stub.StreamObserver<com.demo.grpc.order.Order> responseObserver) {
      asyncUnimplementedUnaryCall(getListOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListOrdersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.demo.grpc.order.Buyer,
                com.demo.grpc.order.Order>(
                  this, METHODID_LIST_ORDERS)))
          .build();
    }
  }

  /**
   * <pre>
   * gRPC服务，这是个在线商城的订单查询服务
   * </pre>
   */
  public static final class OrderQueryStub extends io.grpc.stub.AbstractAsyncStub<OrderQueryStub> {
    private OrderQueryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderQueryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderQueryStub(channel, callOptions);
    }

    /**
     * <pre>
     * 服务端流式：订单列表接口，入参是买家信息，返回订单列表(用stream修饰返回值)
     * </pre>
     */
    public void listOrders(com.demo.grpc.order.Buyer request,
        io.grpc.stub.StreamObserver<com.demo.grpc.order.Order> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getListOrdersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * gRPC服务，这是个在线商城的订单查询服务
   * </pre>
   */
  public static final class OrderQueryBlockingStub extends io.grpc.stub.AbstractBlockingStub<OrderQueryBlockingStub> {
    private OrderQueryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderQueryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderQueryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 服务端流式：订单列表接口，入参是买家信息，返回订单列表(用stream修饰返回值)
     * </pre>
     */
    public java.util.Iterator<com.demo.grpc.order.Order> listOrders(
        com.demo.grpc.order.Buyer request) {
      return blockingServerStreamingCall(
          getChannel(), getListOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * gRPC服务，这是个在线商城的订单查询服务
   * </pre>
   */
  public static final class OrderQueryFutureStub extends io.grpc.stub.AbstractFutureStub<OrderQueryFutureStub> {
    private OrderQueryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderQueryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderQueryFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LIST_ORDERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderQueryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderQueryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_ORDERS:
          serviceImpl.listOrders((com.demo.grpc.order.Buyer) request,
              (io.grpc.stub.StreamObserver<com.demo.grpc.order.Order>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OrderQueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderQueryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.demo.grpc.order.OrderProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderQuery");
    }
  }

  private static final class OrderQueryFileDescriptorSupplier
      extends OrderQueryBaseDescriptorSupplier {
    OrderQueryFileDescriptorSupplier() {}
  }

  private static final class OrderQueryMethodDescriptorSupplier
      extends OrderQueryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderQueryMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OrderQueryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderQueryFileDescriptorSupplier())
              .addMethod(getListOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
