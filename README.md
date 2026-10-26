# purvpn

نسخه کامل تحت نام purvpn

<center>
  <picture>
   <img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/aa.jpg" width="200px">
  </picture>
  <picture>
   <img alt="purvpn" src="https://github.com/davudsedft/purvpn/blob/main/links/cc.jpg" width="200px">
  </picture>
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

## ✨ چرا purvpn؟

- **تنها یک برنامه، ۱۴ پروتکل**: بدون نیاز به نصب چندین کلاینت.
- **مقاوم در برابر DPI**: ترکیب پروتکل‌های پیشرفته مانند AmneziaWG، Hysteria، Paqet و MHRV.
- **ترمینال داخلی بدون root**: اجرای Kali و Molly درون برنامه.
- **متن‌باز و جامعه‌محور**: قدردانی از تمام پروژه‌های متن‌بازی که این هسته را ممکن کرده‌اند.

---

> **purvpn** — یک هسته، تمام نیازهای شما.
