---
marp: true
title: Short and sweet intro to Yubikey OTP
description: Short and sweet intro to Yubikey OTP
theme: uncover
paginate: true
_paginate: false



---

# Short and sweet

## Intro to Yubikey OTP

Close the circuit to generate an OTP sequence followed by Enter

--> use your finger

---

# Intro, reason & background

* multi-factor auth
  * 2nd factor

---

# History

* Smartcard (1975!)
* RSA SecurID
* Yubikey

---

# How it works

Use central authority to verify 2nd factor
* vendor/ online
* self-hosted

---

# How it works (contd.)

![height:500px](images/image.png)

---

# Use case

## CLI

See https://developers.yubico.com/OTP/Specifications/OTP_validation_protocol.html for `status`.

<!--

Request:

curl "https://api.yubico.com/wsapi/2.0/verify?id=1&nonce=1234567890abcdef&otp=<otp>"


Response:

h=jC8SVbHpDt2r45/tIVJ/I2tvKKA=
t=2024-10-05T17:37:48Z0516
otp=<otp>
nonce=1234567890abcdef
status=REPLAYED_OTP
-->

---

# Use case (contd.)

## Quarkus + Qute (no CSS :D )

demo-yubikey

---

# Links

* https://docs.yubico.com/yesdk/users-manual/getting-started/what-is-a-yubikey.html
* https://www.yubico.com/products/yubico-authenticator
* https://en.wikipedia.org/wiki/Smart_card
* https://en.wikipedia.org/wiki/RSA_SecurID
* https://demo.yubico.com/otp/verify
* https://developers.yubico.com/OTP/Guides/Self-hosted_OTP_validation.html

---

# Links (contd.)

* https://www.yubico.com/works-with-yubikey/catalog/?sort=popular
* https://en.wikipedia.org/wiki/Shamir%27s_secret_sharing
* https://en.m.wikipedia.org/wiki/Sun_Ray

---

# Q&A