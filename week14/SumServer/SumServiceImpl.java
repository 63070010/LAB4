package SumServer;


import com.proto.sum.*;
import io.grpc.stub.StreamObserver;


public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver){
        Suming suming = request.getSuming();
        int num1 = suming.getNum1();
        int num2 = suming.getNum2();

        String result = (num1+num2) + "";
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();

        String output = "Server Output : " + num1 + " + " + num2 + " = " + (num1+num2);
        System.out.println(output);
        responseObserver.onNext(response);

        responseObserver.onCompleted();

    }
}
