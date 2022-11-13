if [ -z $1 ]; then
  echo "Run the grpc-client"
  echo "USAGE: $0 [sync|async|stream]"
  exit
fi

if [ $1 == "sync" ]; then
  java -cp target/grpc-client-1.0-SNAPSHOT.jar com.example.grpc.client.sync.Application
elif [ $1 == "async" ]; then
  java -cp target/grpc-client-1.0-SNAPSHOT.jar com.example.grpc.client.async.Application
elif [ $1 == "stream" ]; then
  java -cp target/grpc-client-1.0-SNAPSHOT.jar com.example.grpc.client.stream.server.Application
fi

