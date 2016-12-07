package io.featureflow.client;

        import okhttp3.Headers;

        import java.net.URI;
        import java.util.concurrent.CountDownLatch;

public class DebugClient {
    public static void main(String[] args) throws InterruptedException {
        Headers headers = new Headers.Builder()
                .add("Authorization", "api_key abc123")
                .add("User-Agent", "JavaClient/" + "1.0")
                .add("Accept", "text/event-stream")
                .build();
        EventSource es = new EventSource(URI.create("http://localhost:8081/api/app/v1/controls"), 0l, headers, new EventSourceHandler() {
            @Override
            public void onConnect() {
                System.out.println("CONNECTED");
            }

            @Override
            public void onMessage(String event, MessageEvent message) {
                System.out.println("event = " + event + ", message = " + message.getData());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("ERROR");
                t.printStackTrace();
            }
        });

        es.init();
        new CountDownLatch(1).await();
    }
}