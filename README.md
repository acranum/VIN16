# VIN16 Encryption Algorithm

`vin16` is a custom encryption algorithm designed to securely encrypt and decrypt data. The algorithm aims to provide a balance between security and performance, suitable for various use cases, including file encryption, secure communication, and data protection.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Algorithm Overview](#algorithm-overview)
- [License](#license)
- [Contributing](#contributing)

## Introduction

The `vin16` encryption algorithm is a lightweight and fast cryptographic algorithm designed to provide secure encryption and decryption for a variety of data types. It is intended to be simple to implement and understand while maintaining a high level of security. Since the entire project is open source, it is also extremely customizable.

## Features

- **Lightweight**: Designed to be minimalistic and fast.
- **API**: Easy developer API.
- **Customizable**: Supports various configurations.
- **Secure**: Provides strong encryption to protect data against unauthorized access.

## Installation

To use `vin16`, you can either clone the repository or download the source files. <br>
Downlaod source files: 

```bash
cd vin16
java <vin>
```
or doubleclick .jar <br>
Clone source files: 
```bash
git clone [https://github.com/yourusername/vin16.git](https://github.com/acranum/VIN16.git)
cd vin16
java <vin>
```
## Usage
Add .jar as libary
```bash
import de.acranum.vin;

vin vin = new vin; // create new obj

vin.encrypt(String data, String key);
vin.encrypt(byte[] data, byte[] key);
vin.decrypt(String data, String key);
vin.decrypt(byte[] data, byte[] key);
```

## Algorithm Overview
The vin16 algorithm uses a combination of XOR, SubBytes, ShiftRows and more to provide a secure encryption mechanism. it works by dividing the data into many 16 character packets. These are now encrypted in different rounds with different 16 character keys until a result is obtained after 4 rounds (adjustable). <br>
The key is set to a 16 character length using the md5 hashing algorithm. The optimal key length is still 16 characters

## License
This project is licensed under the MIT License. See the [LICENSE](https://github.com/acranum/VIN16/blob/main/LICENSE) file for details.
