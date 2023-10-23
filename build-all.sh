#!/bin/bash
# build each service

cd TerminalTransactionVerificationService
./build.sh
cd ..

cd TerminalTransactionService
./build.sh
cd ..