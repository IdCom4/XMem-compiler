# XMem

XMem is a binary formating language designed make binary file writing simpler.  
It provides a small set of properties and directives to format you end binary file easily.

---

## 📌 Table of Contents
1. [Introduction](#-introduction)
2. [Syntax](#-syntax)
    - [General Structure](#general-structure)
    - [Properties](#properties)
    - [Directives](#directives)
    - [Comments](#comments)
3. [Output format](#output-format)
4. [Compiler Options](#-compiler-options)
5. [Usage](#usage)

resources:
- [XMem syntax highlight](https://github.com/IdCom4/XMem-syntax-highlight-plugin-intellij) - intellij syntax highlight
---

## 📝 Introduction
XMem is a tool designed to write and format binary file more easily.  

---

## 🔤 Syntax

### General Structure
An `XMem` file consists of at least a byte encoding property, and then a list of hexadecimal values to put into the final binary file.

> ⚠️ All values in an `.xmem` file must be written in hexadecimal.\
> It includes properties and directive parameters

---
### Properties

Properties are descriptive values put at the top of the file, used by the compiler to produce the final binary file.

They are written as follows:
````xmem
$<directive>=<value>
````
Here is the list of **properties**:

| Name            | Description                                                                                                                                                          | Example            | 
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|
| `byte_encoding` | Tells the compiler in how many bytes to encode the values. 1 is the minimum                                                                                          | `$byte_encoding=2` |
| `total_size`    | Tells the compiler the desired final binary file size. If the xmem content isn't enough to produce a binary file the required size, it will fill up the rest with 0s | `$total_size=ffff` |


---

### Directives

Directives are some kind of instructions that do specific tasks.

They are written as follows:
````xmem
@<property>=<value>
````
Here is the list of **directives**:

| Name       | Description                                                                                                                                                            | Example                     | 
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
| `at`       | Tells the compiler to fill the binary with 0s from whatever index it was, up to the index given as parameter.                                                          | `@at=19`                    |
| `relative` | Tells the compiler that all the indexes given as parameter points to relative addresses that must be offset depending on where they currently are on the final binary. | `@relative=[0,7,22,25]`     |
| `inject`   | Injects another `.xmem` file at this place. The path of the other file is given as parameter                                                                           | `@inject="/some/file.xmem"` |

---

### Comments
Comments begin with `#` and extend to the end of the line:

```xmem
# This is a comment
```
---

## Output format

The compiler outputs a simple binary file containing what was in the `.xmem` file, but it starts with a header:
- the first byte contains the byte encoding
- the next 4 bytes contains the size of the file, as the amount of values 
  - = number of bytes / byte encoding

---

## ⚙️ Compiler Options
The **XMem** compiler supports several options:

- `-o=<output_file_name>` – output file name

---

## Usage
```xmem
# test.xmem

$byte_encoding=2
$total_size=ffff

@at=19
@inject="./hello_world.xmem"

8020 0001 0001

```

````shell
$> ./build.sh
$> ./run.sh test.xmem -o=test
````
