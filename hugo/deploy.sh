#!/bin/bash

ROOT_DIR="../$(pwd)"

# create ./public/ release folder
cd blog
rm -rf public
hugo -D

# move release to correct location
cp -r public/* ${ROOT_DIR}
