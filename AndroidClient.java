    public void transferPlaylist(final List<UnifiedTrack> playList) throws IOException {
        Thread thread = new Thread(new Runnable(){
            public void run() {
                try {
                    Log.d("Connecting to server","...");
                    for (UnifiedTrack t :playList) {
                        Socket sock = new Socket("10.0.2.2", 13267);
                        OutputStream os = sock.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);
                        Log.d("Path",t.getLocalTrack().getPath());
                        // sendfile
                        File myFile = new File(t.getLocalTrack().getPath());
                        byte[] mybytearray = new byte[(int) myFile.length()];

                        FileInputStream fis = new FileInputStream(myFile);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        DataInputStream dis = new DataInputStream(bis);
                        dis.readFully(mybytearray, 0, mybytearray.length);


                        // os.write(mybytearray, 0, mybytearray.length);

                        // os.flush();


                        dos.writeUTF(myFile.getName());
                        dos.writeLong(mybytearray.length);
                        dos.write(mybytearray, 0, mybytearray.length);
                        dos.flush();
                        sock.close();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
