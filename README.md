# purvpn
نسخه کامل تحت نام purvpn
<center>
  <picture>
   <img alt="Shows an illustrated sun in light mode and a moon with stars in dark mode." src="https://purnet.ir/vpn/lite2.png" width="200px"  >
</picture>

</center>

برای خروجی گرفتن از کتابخانه گو
go v 1.22

cd v2ray/libs

go mod tidy

go install golang.org/x/mobile/cmd/gomobile@latest

go install golang.org/x/mobile/cmd/gobind@latest

go get golang.org/x/mobile/cmd/gobind

go get golang.org/x/mobile/cmd/gomobile

go get golang.org/x/mobile

gomobile init

gomobile bind -ldflags '-s -w' -o purlast.aar -androidapi 21 -target android .



سورس سرورها
https://raw.githubusercontent.com/davudsedft/newpurnet/refs/heads/main/purkow.txt


طریقه ساخت apk
وارد پوشه root  پروژه شده و در cmd دستور 
cd /purvpn/

```sh
gradlew assembleDebug
```
و در لینوکس در ترمینال 
```sh
./gradlew assembleDebug
```
خروجی در پوشه 
app/build/outputs/apk/release



برای نسخه ریلیس باید در گریدل مشخصات keystore خودتون و وارد فایل گریدل کنین یا نسخه ریلیس را از همینجا دانلود کنین

دستور cmd ویندوز برای خروجی نسخه ریلیس 


```sh
gradlew assembleRelease
```

دستور ترمینال در لینوکس ./ در اول دستور قرار بدین



```sh
./gradlew assembleRelease
```




## Credits
- https://github.com/xtls/xray-core
- https://github.com/2dust/AndroidLibXrayLite
- https://github.com/dev7dev/AndroidLibXrayLite
- https://github.com/gvcgo/vpnparser


