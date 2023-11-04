#!/bin/bash
# build each service

cd TerminalTransactionVerificationService
./build.sh
cd ..

cd WebTransactionService
./build.sh
cd ..