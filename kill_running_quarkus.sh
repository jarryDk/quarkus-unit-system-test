#!/bin/bash

 PID_OF_PORT_IN_USE=$(lsof -i:8080| grep LISTEN | awk '{print $2}')
  if [[ "X" != "X$PID_OF_PORT_IN_USE" ]]; then
    echo "ERROR    - Kill existing Quarkus running on port 8080"
    kill -9 $PID_OF_PORT_IN_USE
  fi