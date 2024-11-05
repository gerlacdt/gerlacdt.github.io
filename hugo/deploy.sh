#!/bin/bash

# create ./public/ release folder
cd blog
rm -rf public/
hugo

# move release to correct location
cp -r public/* ../../blog
rm -rf public/
