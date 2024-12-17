### Insomnia Collection
The insomnia collection for FileTransferMiniProject can be found within the project root directory.

**Filename:** `Insomnia_2024-12-17-FileTransferMiniProject.json`

### File tranfer API Endpoint cURL
```
curl -X POST "http://localhost:8080/api/transfer" \
     -d "filePath=C:\\path\\to\\file.txt" \
     -d "remotePath=/home/username/uploads/file.txt"
```

### How to Use the API
**Endpoint:** `POST /api/transfer`

**Parameters** (via `application/x-www-form-urlencoded` or query params):

- `filePath`: Path of the file on the Windows machine.
- `remotePath`: Destination path on the Linux server (e.g., `/home/username/uploads/file.txt`).

### How It Works

The API takes the `filePath` and `remotePath` from the user.
It validates if the file exists locally on the Windows machine.
The `SftpService` connects to the `Linux server` using **SFTP**.

The file is transferred securely to the `destination path` on the Linux server.

### Configurations in `application.properties`
```
sftp.host=your-linux-server-ip
sftp.port=22
sftp.username=your-username
sftp.password=your-password
```

