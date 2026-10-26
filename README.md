# purvpn

نسخه کامل تحت نام purvpn
<center>
  <table>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/aa.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/cc.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/dd.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/ff.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/gg.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/hh.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/mm.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/nn.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/zz.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/xx.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/vv.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/pp.jpg" width="140px"></td>
    </tr>
    <tr>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/qq.jpg" width="140px"></td>
      <td><img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/rr.jpg" width="140px"></td>
    </tr>
  </table>
</center>
 
---

## 🚀 یک هسته، ۱۴ پروتکل

**purvpn** یک کلاینت یکپارچه و چند‌پروتکلی است که طیف گسترده‌ای از پروتکل‌های عبور از فیلترینگ و تونلینگ را در یک بسته واحد گرد هم آورده است. این پروژه یکی از بزرگ‌ترین هسته‌های یکپارچه را در اختیار کاربر قرار می‌دهد و تمام نیازهای اتصال امن و مقاوم در برابر سانسور را پوشش می‌دهد.

---

## 📦 پروتکل‌های پشتیبانی‌شده

purvpn در مجموع **۱۴ خانواده پروتکل** را پشتیبانی می‌کند:

### ۱. Xray-core
شامل پروتکل‌های **VLESS**، **VMess**، **Trojan**، **Shadowsocks**، **WireGuard**، **HTTP** و **SOCKS**.
> پروتکل‌های HTTP و SOCKS از هسته Xray تأمین می‌شوند.

### ۲. Amnezia (AmneziaWG)
نسخه مقاوم‌شده WireGuard در برابر DPI.

### ۳. DNS Tunneling
شامل **VayDNS**، **MasterDnsVPN** و **Custom DNSTT** برای عبور از فیلترینگ از طریق DNS.

### ۴. SSH
شامل **SSH**، **Stunnel**، **SSH Payload** و **Payload Generator**.
> این بخش از منابع عمومی و بدون رفرنس مشخص پیاده‌سازی شده است.

### ۵. MHRV (MasterHttpRelayVPN)
تونل HTTP/SOCKS از طریق Google Apps Script با Domain Fronting و MITM TLS.

### ۶. SNI Spoof
دستکاری SNI برای دور زدن فیلترینگ.
> بدون رفرنس مشخص.

### ۷. Paqet
تونل مبتنی بر raw socket و KCP برای عبور از DPI و فایروال.

### ۸. Usque
پیاده‌سازی متن‌باز پروتکل **MASQUE** (Cloudflare WARP) مبتنی بر Connect-IP.

### ۹. Psiphon
سیستم دور زدن سانسور با قابلیت‌های متنوع تونلینگ.

### ۱۰. OpenVPN
پروتکل استاندارد VPN.
> بدون رفرنس مشخص.

### ۱۱. SSTP
پروتکل تونلینگ امن Microsoft.
> بدون رفرنس مشخص.

### ۱۲. Hysteria 1
پروکسی مقاوم در برابر سانسور مبتنی بر QUIC سفارشی‌شده.
> برگرفته از پروژه اصلی Hysteria (نه sing-box).

### ۱۳. Hysteria 2
نسل دوم Hysteria با بهینه‌سازی‌های بیشتر.
> برگرفته از پروژه اصلی Hysteria (نه sing-box).

### ۱۴. Tor
شبکه ناشناسی Tor.
> بدون رفرنس مشخص.

---

## 🖥️ ترمینال داخلی

purvpn مجهز به یک **ترمینال قدرتمند** است که بخشی از قابلیت‌های **Termux** در آن ادغام شده است. این ترمینال **بدون نیاز به root** کار می‌کند و امکان اجرای توزیع‌های **Kali Linux** و **Molly** (ویندوز) را فراهم می‌آورد. این ویژگی purvpn را از یک کلاینت معمولی فراتر می‌برد و آن را به یک محیط کامل برای مدیریت شبکه و تست امنیت تبدیل می‌کند.

---

---

## 🛠️ ابزارها و اسکنرها

purvpn تنها یک کلاینت نیست؛ یک جعبه‌ابزار کامل برای مدیریت، بهینه‌سازی و بازیابی اتصال است.

### 🌐 اسکنرها

- **اسکنر IP کلودفلر (Cloudflare IP Scanner)** — پیدا کردن IPهای تمیز و پرسرعت کلودفلر.
- **اسکنر DNS UDP** — اسکن سریع سرورهای DNS از طریق UDP.
- **اسکنر DNS معمولی** — اسکن استاندارد و سریع سرورهای DNS.
- **اسکنر DNS عمیق (Deep DNS Scanner)** — با قابلیت اسکن **۸ میلیون** DNS با سرعت بالا؛ تست‌شده با **۱.۵ میلیون** DNS و تست از تونل **DNSTT** و **VayDNS** با سرعتی خیره‌کننده.
- **اسکنر CDN IP (بخش Psiphon)** — اسکن CDN با سرعت بالا، سقف **۱۰۰٬۰۰۰** IP و تا **۱۰۰۰** پردازش موازی.
- **اسکنرهای Endpoint** — یکپارچه با هسته برای پروتکل **Amnezia** و **Xray**.
- **اسکنرهای پروکسی** — شامل **Proxy Checker** و **DoH Checker** در چهار بخش برنامه.
- **اسکنر تغییر سرور کلودفلر** — در بخش ویرایش سرورها.
- **ویرایش WireGuard با اسکنر Endpoint** — بهینه‌سازی و انتخاب بهترین Endpoint برای WireGuard.

### 🔧 ابزارهای مدیریتی

- **اشتراک تونل با دستگاه‌های دیگر** — به اشتراک گذاشتن تونل بین دستگاه‌ها.
- **رمز کردن سرورها برای پیامک** — محافظت از اطلاعات سرورها.
- **بک‌اپ از تنظیمات** — پشتیبان‌گیری و بازیابی کامل تنظیمات.
- **تغییر سورس سرورها به هاست داخلی** — مناسب دوران **نت ملی**.
- **تبادل اطلاعات و چت روی هاست داخلی** — ارتباط امن روی هاست داخلی.


---

## 🗂️ مخازن (Repositories)

purvpn یک سیستم مدیریت مخازن سرور قدرتمند و مقیاس‌پذیر در اختیار شما می‌گذارد که برای کار با هزاران سرور طراحی شده است.

### ➕ افزودن ساب و مخازن
- افزودن **ساب** و **مخازن سرور** با پشتیبانی از سرورهای مرسوم.
- پشتیبانی از لینک‌هایی که توسط توسعه‌دهنده ساخته شده‌اند.
- **لینک‌های اختصاصی** شامل:
  - `ssh://`
  - `sstp://`
  - `w2w://`
  - `purguard://`

### 📥 ظرفیت و مقیاس
- قابلیت لود مخازن تا سقف **۵۰٬۰۰۰ سرور**.
- دریافت سرور از **فایل‌های تست** تا سقف **نامحدود**.
- تست‌شده تا **۲۰۰٬۰۰۰ سرور**.

### 🔍 جستجو و مدیریت
- **سرچ قدرتمند** در بین تعداد زیاد سرور.
- جستجو در **مخازن** و جستجو در **سرورها**.
- **تست پینگ در پس‌زمینه** بدون توقف کار برنامه.

### 🔀 ترکیب و هدایت ترافیک
- **ترکیب پروتکل‌ها با یکدیگر** برای سناریوهای پیچیده.
- **هدایت ترافیک** از سرورهای مخازن به سایر پروتکل‌ها.
- **در هم تنیده شدن پروتکل‌ها** برای افزایش مقاومت در برابر فیلترینگ و DPI.





## 🔗 منابع و اعتبارها (Credits)

### هسته‌های اصلی
- [Xray-core](https://github.com/xtls/xray-core)
- [AndroidLibXrayLite](https://github.com/2dust/AndroidLibXrayLite)
- [AndroidLibXrayLite (dev7dev)](https://github.com/dev7dev/AndroidLibXrayLite)
- [vpnparser](https://github.com/gvcgo/vpnparser)
- [telegram-configs-collector](https://github.com/soroushmirzaei/telegram-configs-collector)

### پروتکل‌های دارای منبع مشخص
- **AmneziaWG**: [amnezia-vpn/amneziawg-go](https://github.com/amnezia-vpn/amneziawg-go)
- **VayDNS**: [net2share/vaydns](https://github.com/net2share/vaydns)
- **MasterDnsVPN**: [masterking32/MasterDnsVPN](https://github.com/masterking32/MasterDnsVPN)
- **MHRV**: [masterking32/MasterHttpRelayVPN](https://github.com/masterking32/MasterHttpRelayVPN)
- **Paqet**: [hanselime/paqet](https://github.com/hanselime/paqet)
- **Usque**: [Diniboy1123/usque](https://github.com/Diniboy1123/usque)
- **Psiphon**: [Psiphon-Labs/psiphon-tunnel-core](https://github.com/Psiphon-Labs/psiphon-tunnel-core)
- **Hysteria 1 & 2**: [apernet/hysteria](https://github.com/apernet/hysteria)

### بخش‌های بدون رفرنس مشخص
پروتکل‌های **SSH** (شامل Stunnel، SSH Payload و Payload Generator)، **SNI Spoof**، **OpenVPN**، **SSTP** و **Tor** در این پروژه از منابع عمومی و بدون رفرنس مشخص استفاده کرده‌اند.

---

## ✨ یک برنامه، ۱۴ پروتکل: بدون نیاز به نصب چندین کلاینت.
- **مقاوم در برابر DPI: ترکیب پروتکل‌های پیشرفته مانند AmneziaWG، Hysteria، Paqet و MHRV.
- **ترمینال داخلی بدون root**: اجرای Kali و انواع توزیهای لینوکسی  درون ترمینال برنامه.
- **متن‌باز و جامعه‌محور**: قدردانی از تمام پروژه‌های متن‌بازی که این هسته را ممکن کرده‌اند.

---

> **purvpn** — یک هسته، تمام نیازهای شما.
