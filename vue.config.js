module.exports = {
    assetsDir: "assert",
    outputDir: "src/main/resources/static",
    devServer: {
        port: 3000,
        proxy: {
            '/editing_center': {
              target: 'http://localhost:8080',
              ws: true,
              changeOrigin: true
            },
            '/files': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }      
    }
  }